//
//  PersonAddFavouriteService.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 29/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation
import Alamofire
import SwiftUI

class PersonAddFavouriteService:ObservableObject{
    @ObservedObject var viewRouter: ViewRouter
    var checkTokenService:CheckTokenService
    
    
    init(viewRouter: ViewRouter) {
        self.viewRouter = viewRouter;
        checkTokenService = CheckTokenService(viewRouter: viewRouter)
    }
    
    func addToFavourite(userId:String,isFavourite:Int, completionHandler: @escaping(PeopleResponse)->Void) -> Void {
        checkTokenService.CheckToken { (checkStatus) in
            if(checkStatus){
                let headers: HTTPHeaders = [
                    "Authorization": "Bearer \(UserDefaults.standard.string(forKey: "access_token")!)",
                    "Accept": "application/json"
                ]
                let parameters = [
                    "userId" : userId,
                    "isFavourite" : "\(isFavourite)"
                ]
                AF.request(EndPoints.AD_PERSON_TO_FAVOURITE, method: .post, parameters: parameters,encoding: JSONEncoding.default, headers: headers)
                    .responseData { (response) in
                        if(response.data != nil){
                            do{
                                let jsonDecoder = JSONDecoder()
                                let parsedData = try jsonDecoder.decode(PeopleResponse.self, from: response.data!)
                                completionHandler(parsedData)
                            }catch{
                                let parsedData = PeopleResponse()
                                parsedData.error = "Unknown error has occurred!"
                                completionHandler(parsedData)
                            }
                        }else{
                            let parsedData = PeopleResponse()
                            parsedData.error = "Unknown error has occurred!"
                            completionHandler(parsedData)
                        }
                }
            }
        }
    }
}

