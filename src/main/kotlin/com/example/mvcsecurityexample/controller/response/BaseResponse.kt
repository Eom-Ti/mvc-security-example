package com.example.mvcsecurityexample.controller.response

data class BaseResponse<T> (
    val code: Int,
    val msg: String,
    val data: T?
) {
    companion object {
        fun <T> ok(data: T): BaseResponse<T> {
            return BaseResponse(ResponseCode.SUCCESS.code, ResponseCode.SUCCESS.msg, data)
        }
    }
}
