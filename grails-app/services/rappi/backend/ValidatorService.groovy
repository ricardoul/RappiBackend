package rappi.backend

import grails.transaction.Transactional

class ValidatorService {

	def parserService

	static transactional = false 

	static Integer MATRIX_MAX_SIZE = 50
	static Integer SETVALUE_MAX_SIZE = 10**9 //(10^9)
	static Integer OP_MAX_COUNT = 100
	static Integer MATRIX_MIN_SIZE = 1


	def validate(testData){
		validateSize(testData.matrixSize)
		validateOpCount(testData.opCount)
		validateOperations(testData)
	}

	def validateOperations(testData){
    	for(operation in testData.operations){
    		def parsedOp = parserService.parseOperation(operation)
    		if(parsedOp.action == 'QUERY'){
    			validateQueryOp(parsedOp, testData)
    		}else if(parsedOp.action == 'UPDATE'){
    			validateUpdateOp(parsedOp, testData)
    		}
    	}
    }

    def validateQueryOp(op, testData) {
    	validateOverflowValues(op.values, testData)
    }

    def validateUpdateOp(op, testData) {
    	validateOverflowValues(op.values[0..op.values.size()-2], testData)
    	validateSetValue(op.values[op.values.size()-1])
    }

    def validateSize(size){
    	if(MATRIX_MIN_SIZE <= size  && size <= MATRIX_MAX_SIZE ){
    		return true
    	}
    	else{
    		throw new Exception("Invalid Size ${size}")
    	}
	}

	def validateOpCount(opCount){
    	if(MATRIX_MIN_SIZE <= opCount && opCount <= OP_MAX_COUNT ){
    		return true
    	}
    	else{
    		throw new Exception("Invalid OpCount")
    	}
	}

	def validateCordPairs(cord1, cord2){
    	if(1 <= cord1 && cord1 <= cord2 && cord2 <= N){
    		return true
    	}
    	else{
    		throw new Exception("Invalid Cords on ops")
    	}
	}

    def validateOverflowValues(values, testData){
    	def findBadValues = values.findAll{it > testData.matrixSize || it < 1}
    	if(findBadValues.size() >0){
    		throw new Exception("Value overflow matrix ${findBadValues} matrixSize: ${testData.matrixSize}")
    	}
    }

    def validateSetValue(valToSet){
    	if(-SETVALUE_MAX_SIZE <= valToSet && valToSet <= SETVALUE_MAX_SIZE){
    		return true
    	}else{
    		throw new Exception("Value toSet overflow")
    	}
    }
}
