package ivan.projects.networkutils.retrofit

import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class NetworkResponseAdapterFactory : CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit,
    ): CallAdapter<*, *>? {
        if(Call::class.java != getRawType(returnType))
            return null
        check(returnType is ParameterizedType){
            "return type must be parametrized as call<NetworkResponse<Foo>>"
        }
        val responseType = getParameterUpperBound(0, returnType)
        if(getRawType(responseType) != NetworkResponse::class.java)
            return null
        check(responseType is ParameterizedType){
            "Response must be parameterized as NetworkResponse<Foo>"
        }
        // первый параметр responseType это тип успешного ответа
        val successBodyType = getParameterUpperBound(0, responseType)
        // второй параметр responseType это тип ошибки
        val errorBodyType = getParameterUpperBound(1, responseType)


        // получаем дефолтный конвертер, который ResponseBody -> error
        val errorBodyConverter = retrofit.nextResponseBodyConverter<Any>(null, errorBodyType, annotations)

        return NetworkResponseAdapter<Any, Any>(successBodyType, errorBodyConverter)
    }
}