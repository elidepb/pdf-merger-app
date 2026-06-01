package com.app.fusionarpdfs.domain.model

class PdfValidationException(val error: PdfFileError) : Exception()
