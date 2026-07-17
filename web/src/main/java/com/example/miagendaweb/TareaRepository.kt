package com.example.miagendaweb

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TareaRepository : JpaRepository<Tarea, Long>
