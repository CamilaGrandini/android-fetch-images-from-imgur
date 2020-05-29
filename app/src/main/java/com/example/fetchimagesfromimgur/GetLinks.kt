package com.example.fetchimagesfromimgur

import org.json.JSONObject

class GetLinks {
    companion object {
        fun get(jsonObject: JSONObject): ArrayList<String> {

            var catsLinks = ArrayList<String>()
            var jsonArray = jsonObject.getJSONArray("data")

             for(i in 0 until  jsonArray.length() ){
                var imagesObject = JSONObject(jsonArray.get(i).toString())
                if(imagesObject.has("images")) {
                    var imagesArray = imagesObject.getJSONArray("images")
                    for(x in 0 until  imagesArray.length() ){
                        if (JSONObject(imagesArray[x].toString()).get("type").toString().contains("image")) {
                            if(JSONObject(imagesArray[x].toString()).get("link").toString().isNotEmpty()) {
                                var link = JSONObject(imagesArray[x].toString()).get("link").toString()
                                catsLinks.add(link)
                            }
                        }
                    }
                }
            }
            return catsLinks
        }
    }
}