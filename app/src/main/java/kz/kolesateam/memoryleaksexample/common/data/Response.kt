package kz.kolesateam.memoryleaksexample.common.data

sealed class Response<out Result, out Error> {

    data class Success<Result>(
            val result: Result
    ) : Response<Result, Nothing>()

    data class Error<Error>(
            val error: Error
    ) : Response<Nothing, Error>()
}