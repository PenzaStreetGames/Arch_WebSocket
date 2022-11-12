package com.penzastreet.spring_web_socket.services

import com.penzastreet.spring_web_socket.models.KeyValue
import com.penzastreet.spring_web_socket.repositories.KeyValueRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class KeyValueService(private var repository: KeyValueRepository) {

    fun setKeyValue(kv: KeyValue): KeyValue {
        return repository.save(kv)
    }

    fun getValueByKey(key: String): KeyValue? {
        return repository.getByKey(key)
    }

    fun deleteKeyValueByKey(key: String) {
        repository.deleteByKey(key)
    }

    fun getKeyValueSet(): List<KeyValue> {
        return repository.getAll();
    }
}