package com.ab.data.model.mappers

import com.ab.data.model.request.LoginRequest
import com.ab.domain.model.form.LoginForm

fun LoginForm.toRequest() = LoginRequest(email, password)