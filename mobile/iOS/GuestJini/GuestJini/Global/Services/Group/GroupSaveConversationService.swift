//
//  GroupSaveConversationService.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 30/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation
import Alamofire
import SwiftUI

class GroupSaveConversationService:ObservableObject{
    @Published var groupConversationResponse = GroupConversationResponse()
    @ObservedObject var viewRouter: ViewRouter
    @Published var groupConversation:GroupConversation = GroupConversation()
    @Published var fetchComplete:Bool = false
    
    var checkTokenService:CheckTokenService
    
    init(viewRouter: ViewRouter) {
        self.viewRouter = viewRouter;
        self.checkTokenService = CheckTokenService(viewRouter: viewRouter)
        
    }
    
    func saveConversation(groupId:String, message:String, completionHandler: @escaping(GroupConversationResponse)->Void) -> Void {
        checkTokenService.CheckToken { (checkStatus) in
            if(checkStatus){
                let headers: HTTPHeaders = [
                    "Authorization": "Bearer \(UserDefaults.standard.string(forKey: "access_token")!)",
                    "Accept": "application/json"
                ]
                let parameters = [
                    "groupId" : groupId,
                    "message" : message
                ]
                AF.request(EndPoints.SAVE_GROUP_CONVERSATION, method: .post, parameters: parameters,encoding: JSONEncoding.default, headers: headers)
                    .responseData { (response) in
                        if(response.data != nil){
                            do{
                                let jsonDecoder = JSONDecoder()
                                let parsedData = try jsonDecoder.decode(GroupConversationResponse.self, from: response.data!)
                                completionHandler(parsedData)
                            }catch{
                                let parsedData = GroupConversationResponse()
                                parsedData.error = "Unknow error has occurred!"
                                completionHandler(parsedData)
                            }
                        }else{
                            let parsedData = GroupConversationResponse()
                            parsedData.error = "Unknow error has occurred!"
                            completionHandler(parsedData)
                        }
                }
            }
        }
    }
}
