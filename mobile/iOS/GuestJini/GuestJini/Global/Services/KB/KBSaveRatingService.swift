//
//  KBSaveRatingService.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 22/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation
import Alamofire
import SwiftUI

class KBSaveRatingService:ObservableObject{
    
    @ObservedObject var viewRouter: ViewRouter
    var checkTokenService:CheckTokenService
    
    init(viewRouter: ViewRouter) {
        self.viewRouter = viewRouter;
        self.checkTokenService = CheckTokenService(viewRouter: viewRouter)
    }
    
    func saveRating(kbRating:KBRating, completionHandler: @escaping(KBRatingResponse)->Void) -> Void {
        checkTokenService.CheckToken { (checkStatus) in
            if(checkStatus){
                let headers: HTTPHeaders = [
                    "Authorization": "Bearer \(UserDefaults.standard.string(forKey: "access_token")!)",
                    "Accept": "application/json"
                ]
                let jsonEncoder = JSONEncoder()
                let kbRatingData = try! jsonEncoder.encode(kbRating)
                let json = try! JSONSerialization.jsonObject(with: kbRatingData, options: []) as? [String : Any]
                 AF.request(EndPoints.KB_SAVE_RATING, method: .post, parameters: json,encoding: JSONEncoding.default, headers: headers)
                    .responseData { (response) in
                        let jsonDecoder = JSONDecoder()
                        do{
                            let parsedData =  try jsonDecoder.decode(KBRatingResponse.self, from: response.data!)
                            completionHandler(parsedData)
                        }catch{
                            let parsedData = KBRatingResponse()
                            parsedData.error = "Unknow error has occrred"
                            parsedData.success = false;
                            completionHandler(parsedData)
                        }
                        
                }
                
                
            }
        }
    }
    
}

