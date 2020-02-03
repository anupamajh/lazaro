//
//  CreateGroupService.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 01/02/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation
import Alamofire
import SwiftUI

class CreateGroupService:ObservableObject{
    @ObservedObject var viewRouter: ViewRouter
    @Published var groupResponse = GroupResponse()
    @Published var groupConversation:GroupConversation = GroupConversation()
    @Published var fetchComplete:Bool = false
    
    var checkTokenService:CheckTokenService
    
    init(viewRouter: ViewRouter) {
        self.viewRouter = viewRouter;
        self.checkTokenService = CheckTokenService(viewRouter: viewRouter)
        
    }
    
    func createGroup(group:GroupModel, completionHandler: @escaping(GroupResponse)->Void) -> Void {
        checkTokenService.CheckToken { (checkStatus) in
            if(checkStatus){
                let headers: HTTPHeaders = [
                    "Authorization": "Bearer \(UserDefaults.standard.string(forKey: "access_token")!)",
                    "Accept": "application/json"
                ]
                let jsonEncoder = JSONEncoder()
                let groupData = try! jsonEncoder.encode(group)
                let json = try! JSONSerialization.jsonObject(with: groupData, options: []) as? [String : Any]
                
                AF.request(EndPoints.SAVE_GROUP, method: .post, parameters: json,encoding: JSONEncoding.default, headers: headers)
                    .responseData { (response) in
                            if(response.data != nil){
                            do{
                                let jsonDecoder = JSONDecoder()
                                let parsedData = try jsonDecoder.decode(GroupResponse.self, from: response.data!)
                                completionHandler(parsedData)
                            }catch{
                                let parsedData = GroupResponse()
                                parsedData.error = "Unknow error has occurred!"
                                completionHandler(parsedData)
                            }
                        }else{
                            let parsedData = GroupResponse()
                            parsedData.error = "Unknow error has occurred!"
                            completionHandler(parsedData)
                        }
                }
            }
        }
    }
}
