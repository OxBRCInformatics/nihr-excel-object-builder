package nihr.excelloader.nihr.excelloader

/**
 * Created by rb on 11/03/2014.
 */
class SchemaGenerator {

	/**
	 * Not working yet :(
	 */

	List normalise(def headers, List objects){

		def commonValues = [:]

		def commonContent = [:]
		def itemContentCount = [:]

		headers.eachWithIndex { def header, int i ->
			commonValues[i] = []
			commonContent[i as String] = [:]
		}

		int newHeaderId = 1

		objects.each { object ->
			object.eachWithIndex { item, index ->
				if(item){
					String indexStr = index as String
					if(!itemContentCount[indexStr]) {
						itemContentCount[indexStr] = [:]
					}
					itemContentCount[indexStr][item] = itemContentCount[indexStr][item]?: 0
					itemContentCount[indexStr][item] ++

					if(itemContentCount[indexStr][item] > 1){
						if(!headers[index]){
							headers[index] = "Additional item " +newHeaderId
							newHeaderId++
							commonValues[index] = []
							commonContent[indexStr][item] = true
						}
						println "Saw more than 1 " + item + ": " + itemContentCount[indexStr][item]

					}
				}
			}
		}



		List responseObjects = []
		objects.each { object ->
			def responseModel = [:]
			object.eachWithIndex { item, index ->
				def label = headers[index]
				if(commonContent[index as String] && commonContent[index as String][label]){
					if(!commonValues[label].contains(item)){
						commonValues[label].add(item)
					}
					int indexOfItem = commonValues[label].indexOf(item)
					responseModel[label] = [ id: indexOfItem ]
				}else{
					responseModel[label] = item
				}
			}
			responseObjects.add(responseModel)
		}

		return responseObjects
	}
}
