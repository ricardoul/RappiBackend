package rappi.backend

import grails.transaction.Transactional
import grails.converters.JSON

class CubeService {
	static transactional = false

	def parserService
	def searchService

    def calculate(text) {
    	println "hola"
    	log.info "hola"
    	log.error "hola"
    	def parsedStructure= parserService.parseStructure(parserService.parseText(text))

    	for(test in parsedStructure.tests){
    		executeTest(test)
    	}
    }

    def executeTest(test){
    	for(operation in test.operations){
    		def parsedOp = parserService.parseOperation(operation)
    		println parsedOp.action
    		if(parsedOp.action == 'QUERY'){
    			//searchService.queryObjects(parsedOp)

    		}else if(parsedOp.action == 'UPDATE'){
    			createObjects(parsedOp)
    		}
    	}
    }

    def createObjects(object){
    	def matrix = new Matrix(x:object.values[0] as Long, y:object.values[1] as Long, z:object.values[2] as Long, val:object.values[3] as Long)
    	matrix.save(failOnError:true, flush:true)
    }

}