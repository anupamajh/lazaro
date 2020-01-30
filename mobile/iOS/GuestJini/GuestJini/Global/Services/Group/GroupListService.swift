//
//  GroupListService.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 30/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation
import Alamofire
import SwiftUI

class GroupListService:ObservableObject{
    @Published var groupResponse = GroupResponse()
    @ObservedObject var viewRouter: ViewRouter
    @Published var fetchComplete:Bool = false
    
    var checkTokenService:CheckTokenService
    
    init(viewRouter: ViewRouter,groupType:Int) {
        self.viewRouter = viewRouter;
        self.checkTokenService = CheckTokenService(viewRouter: viewRouter)
        self.getGroupByType(groupType:groupType) { (response) in
            self.groupResponse = response
            self.fetchComplete = true
        }
    }
    
    func getGroupByType(groupType:Int, completionHandler: @escaping(GroupResponse)->Void) -> Void {
        checkTokenService.CheckToken { (checkStatus) in
            if(checkStatus){
                let headers: HTTPHeaders = [
                    "Authorization": "Bearer \(UserDefaults.standard.string(forKey: "access_token")!)",
                    "Accept": "application/json"
                ]
                
                let parameters = ["groupType" : "\(groupType)"]
                AF.request(EndPoints.GET_GROUP_LIST_BY_TYPE, method: .post, parameters: parameters,encoding: JSONEncoding.default, headers: headers)
                    .responseData { (response) in
                        do{
                            if(response.data == nil){
                                let parsedData = GroupResponse()
                                parsedData.error = "Unknown error has occurred"
                                completionHandler(parsedData)
                            }else{
                                let jsonDecoder = JSONDecoder()
                                let parsedData = try jsonDecoder.decode(GroupResponse.self, from: response.data!)
                                completionHandler(parsedData)
                            }
                        }catch{
                            let parsedData = GroupResponse()
                            parsedData.error = "Unknown error has occurred"
                            completionHandler(parsedData)
                        }
                }
            }
        }
        
    }
}
