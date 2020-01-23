//
//  KBGetService.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 22/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation
import Alamofire
import SwiftUI

class KBGetService:ObservableObject{
    @Published var kbResponse = KBResponse()
    @ObservedObject var viewRouter: ViewRouter
    @Published var kb:KB = KB()
    @Published var fetchComplete:Bool = false
    
    var checkTokenService:CheckTokenService
    
    init(viewRouter: ViewRouter, id:String) {
        self.viewRouter = viewRouter;
        self.checkTokenService = CheckTokenService(viewRouter: viewRouter)
        self.getKB(id: id) { (response) in
            self.kbResponse = response
            self.kb = response.kb!;
            self.fetchComplete = true
        }
    }
    
    func getKB(id:String, completionHandler: @escaping(KBResponse)->Void) -> Void {
        checkTokenService.CheckToken { (checkStatus) in
            if(checkStatus){
                let headers: HTTPHeaders = [
                    "Authorization": "Bearer \(UserDefaults.standard.string(forKey: "access_token")!)",
                    "Accept": "application/json"
                ]
                
                let parameters = ["id" : id]
                AF.request(EndPoints.KB_GET_URL, method: .post, parameters: parameters,encoding: JSONEncoding.default, headers: headers)
                    .responseData { (response) in
                        let jsonDecoder = JSONDecoder()
                        do{
                            let parsedData = try jsonDecoder.decode(KBResponse.self, from: response.data!)
                            completionHandler(parsedData)
                        }catch{
                            let parsedData = KBResponse()
                            completionHandler(parsedData)
                        }
                        
                }
            }
            
        }
    }
}
