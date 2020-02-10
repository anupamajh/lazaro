//
//  SaveDviceDataService.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 08/02/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation
import Alamofire
import SwiftUI

class SaveDviceDataService:ObservableObject{
    @ObservedObject var viewRouter: ViewRouter
    var checkTokenService:CheckTokenService
    
    init(viewRouter: ViewRouter) {
        self.viewRouter = viewRouter;
        self.checkTokenService = CheckTokenService(viewRouter: viewRouter)
    }
    
    func saveDeviceData(userDeviceData:UserDeviceData, completionHandler: @escaping(UserDeviceDataResponse)->Void) -> Void {
        checkTokenService.CheckToken { (checkStatus) in
            if(checkStatus){
                let headers: HTTPHeaders = [
                    "Authorization": "Bearer \(UserDefaults.standard.string(forKey: "access_token")!)",
                    "Accept": "application/json"
                ]
                let jsonEncoder = JSONEncoder()
                let userDeviceDataJson = try! jsonEncoder.encode(userDeviceData)
                let json = try! JSONSerialization.jsonObject(with: userDeviceDataJson, options: []) as? [String : Any]
                 AF.request(EndPoints.SAVE_USER_DEVICE_DATA, method: .post, parameters: json,encoding: JSONEncoding.default, headers: headers)
                    .responseData { (response) in
                        let jsonDecoder = JSONDecoder()
                        do{
                            if(response.data != nil){
                                let parsedData =  try jsonDecoder.decode(UserDeviceDataResponse.self, from: response.data!)
                                completionHandler(parsedData)
                            }else{
                                let parsedData = UserDeviceDataResponse()
                                parsedData.error = "Unknow error has occrred"
                                parsedData.success = false;
                                completionHandler(parsedData)
                            }
                        }catch{
                            let parsedData = UserDeviceDataResponse()
                            parsedData.error = "Unknow error has occrred"
                            parsedData.success = false;
                            completionHandler(parsedData)
                        }
                        
                }
                
                
            }
        }
    }
    
}
