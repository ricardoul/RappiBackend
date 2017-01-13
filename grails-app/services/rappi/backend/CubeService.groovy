package rappi.backend

import grails.transaction.Transactional
import grails.converters.JSON

class CubeService {
	static transactional = false

	def parserService
	def searchService
	def validatorService

    def calculate(text) {
    	def responseJson = []
		def parsedStructure= parserService.parseStructure(parserService.parseText(text))
		for(test in parsedStructure.tests){
			validatorService.validate(test)
			responseJson += executeTest(test)
			cleanMatrix()
		}
		return responseJson
    }

    def cleanMatrix(){
    	Matrix.findAll().each{
    		it.delete(flush:true)
    	}
    }

    def executeTest(test){
    	def testResult = []
    	for(operation in test.operations){
    		def parsedOp = parserService.parseOperation(operation)
    		if(parsedOp.action == 'QUERY'){
    			def queryRes = searchService.queryObjects(parsedOp)
    			def result = queryRes?.collect{it.val}?.sum()?: 0
    			println "resultado query : ${result}"
    			testResult += [result: result, op: parsedOp]
    		}else if(parsedOp.action == 'UPDATE'){
    			createOrUpdateObjects(parsedOp)
    		}
    	}
    	return testResult
    }

    def createOrUpdateObjects(object){
    	def x = object.values[0] as Long
    	def y = object.values[1] as Long
    	def z = object.values[2] as Long
    	def matrix
    	def matrixSearch = Matrix.findAllByXAndYAndZ(x, y, z)
    	if(matrixSearch.size() == 0){
    		matrix = new Matrix(x: x, y: y as Long, z: z, val:object.values[3] as Long)
    	}else{
    		matrix = matrixSearch[0]
    		matrix.val = object.values[3] as Long
    	}
    	matrix.save(failOnError:true, flush:true)
    }

}