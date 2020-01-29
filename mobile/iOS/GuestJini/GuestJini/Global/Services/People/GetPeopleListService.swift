//
//  GetPeopleListService.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 28/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation
import Alamofire
import SwiftUI

class GetPeopleListService:ObservableObject{
    @Published var peopleResponse = PeopleResponse()
    @ObservedObject var viewRouter: ViewRouter
    @Published var myTotalInterests:Int = 0;
    @Published var fetchComplete:Bool = false
    
    var checkTokenService:CheckTokenService
    
    init(viewRouter: ViewRouter) {
        self.viewRouter = viewRouter;
        self.checkTokenService = CheckTokenService(viewRouter: viewRouter)
        self.getPeopleList { (response) in
            self.peopleResponse = response
            if(response.myInterests != nil){
            self.myTotalInterests = response.myInterests!.count
            }
            self.fetchComplete = true
        }
    }
    
    func getPeopleList(completionHandler: @escaping(PeopleResponse)->Void) -> Void {
        checkTokenService.CheckToken { (checkStatus) in
            if(checkStatus){
                let headers: HTTPHeaders = [
                    "Authorization": "Bearer \(UserDefaults.standard.string(forKey: "access_token")!)",
                    "Accept": "application/json"
                ]
                
                let parameters = ["" : ""]
                AF.request(EndPoints.GET_LIST_OF_PEOPLE, method: .post, parameters: parameters,encoding: JSONEncoding.default, headers: headers)
                    .responseData { (response) in
                        do{
                            if(response.data == nil){
                                let parsedData = PeopleResponse()
                                parsedData.error = "Unknown error has occurred"
                                completionHandler(parsedData)
                            }else{
                                let jsonDecoder = JSONDecoder()
                                let parsedData = try jsonDecoder.decode(PeopleResponse.self, from: response.data!)
                                completionHandler(parsedData)
                            }
                        }catch{
                            let parsedData = PeopleResponse()
                            parsedData.error = "Unknown error has occurred"
                            completionHandler(parsedData)
                        }
                }
            }
        }
        
    }
}

