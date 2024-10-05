package com.example.backup.exception


class EntityNotFoundException : RuntimeException()

class DuplicateEntityException : RuntimeException()

class InactiveDatabaseException() : RuntimeException()