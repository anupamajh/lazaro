//
//  KBGetRatingService.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 22/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation
import Alamofire
import SwiftUI

class KBGetRatingService:ObservableObject{
    @Published var kbRatingResponse = KBRatingResponse()
    @ObservedObject var viewRouter: ViewRouter
    @Published var kbRating:KBRating = KBRating()
    @Published var fetchComplete:Bool = false
    
    var checkTokenService:CheckTokenService
    
    init(viewRouter: ViewRouter, kbId:String) {
        self.viewRouter = viewRouter;
        self.checkTokenService = CheckTokenService(viewRouter: viewRouter)
        self.getKB(kbId: kbId) { (response) in
            self.kbRatingResponse = response
            if(response.kbRating == nil){
                self.kbRating = KBRating()
            }else{
                self.kbRating = response.kbRating!
            }
            self.fetchComplete = true
        }
    }
    
    func getKB(kbId:String, completionHandler: @escaping(KBRatingResponse)->Void) -> Void {
        checkTokenService.CheckToken { (checkStatus) in
            if(checkStatus){
                let headers: HTTPHeaders = [
                    "Authorization": "Bearer \(UserDefaults.standard.string(forKey: "access_token")!)",
                    "Accept": "application/json"
                ]
                
                let parameters = ["kbId" : kbId]
                AF.request(EndPoints.KB_GET_RATING, method: .post, parameters: parameters,encoding: JSONEncoding.default, headers: headers)
                    .responseData { (response) in
                        let jsonDecoder = JSONDecoder()
                        do{
                            let parsedData = try jsonDecoder.decode(KBRatingResponse.self, from: response.data!)
                            completionHandler(parsedData)
                        }catch{
                            let parsedData = KBRatingResponse()
                            completionHandler(parsedData)
                        }
                        
                }
            }
            
        }
    }
}

