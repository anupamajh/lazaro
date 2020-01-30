//
//  KBListService.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 21/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation
import Alamofire
import SwiftUI

class KBListService:ObservableObject{
    @Published var kbResponse = KBResponse()
    @ObservedObject var viewRouter: ViewRouter
    @Published var kbList:[KB] = []
    @Published var fetchComplete:Bool = false
    
    var checkTokenService:CheckTokenService
    
    init(viewRouter: ViewRouter) {
        self.viewRouter = viewRouter;
        self.checkTokenService = CheckTokenService(viewRouter: viewRouter)
        self.getKBList { (response) in
            self.kbResponse = response
            if(response.success){
                self.kbList = response.kbList!;
            }else{
                self.kbList  = []
            }
            self.fetchComplete = true
        }
    }
    
    func getKBList(completionHandler: @escaping(KBResponse)->Void) -> Void {
        checkTokenService.CheckToken { (checkStatus) in
            if(checkStatus){
                let headers: HTTPHeaders = [
                    "Authorization": "Bearer \(UserDefaults.standard.string(forKey: "access_token")!)",
                    "Accept": "application/json"
                ]
                
                let parameters = ["" : ""]
                AF.request(EndPoints.KB_LIST_URL, method: .post, parameters: parameters,encoding: JSONEncoding.default, headers: headers)
                    .responseData { (response) in
                        let jsonDecoder = JSONDecoder()
                        do{
                            if(response.data != nil){
                                let parsedData = try jsonDecoder.decode(KBResponse.self, from: response.data!)
                                completionHandler(parsedData)
                            }else{
                                let parsedData = KBResponse()
                                parsedData.error = "Unknow error has occurred"
                                completionHandler(parsedData)
                            }
                        }catch{
                            let parsedData = KBResponse()
                            parsedData.error = "Unknow error has occurred"
                            completionHandler(parsedData)
                        }
                        
                }
            }
            
        }
    }
}
