package rappi.backend

import grails.transaction.Transactional

class ParserService {

	static transactional = false 

	def parseOperation(rawOperation){
    	def op = rawOperation.split(' ')
    	return [action: op[0].trim(), values:op[1..op.size()-1].collect{it as Integer}]

    }

    def parseText(text){
    	return text.split('\n')
    }

    def parseStructure(list){
    	def parsedMap = [:]
    	parsedMap.testCount = list[0] as Integer
    	parsedMap.tests = []
    	def counter = 1
    	for(i in 1..parsedMap.testCount){
    		def partialMap = [:]
	    	partialMap.matrixSize = list[counter].split(' ')[0].trim() as Integer
	    	partialMap.opCount = (list[counter].split(' ')[1].trim()) as Integer
	    	counter +=1
	    	partialMap.operations = list[counter ..counter + (partialMap.opCount -1 )]
	    	parsedMap.tests +=partialMap
	    	counter += (partialMap.opCount as Integer )
    	}

    	return parsedMap
    }

}
