//
//  KBSaveReviewService.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 22/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation
import Alamofire
import SwiftUI

class KBSaveReviewService:ObservableObject{
    @ObservedObject var viewRouter: ViewRouter
    var checkTokenService:CheckTokenService
    
    init(viewRouter: ViewRouter) {
        self.viewRouter = viewRouter;
        self.checkTokenService = CheckTokenService(viewRouter: viewRouter)
    }
    
    func saveReview(kbReview:KBReview, completionHandler: @escaping(KBResponse)->Void) -> Void {
        checkTokenService.CheckToken { (checkStatus) in
            if(checkStatus){
                let headers: HTTPHeaders = [
                    "Authorization": "Bearer \(UserDefaults.standard.string(forKey: "access_token")!)",
                    "Accept": "application/json"
                ]
                let jsonEncoder = JSONEncoder()
                let kbReviewData = try! jsonEncoder.encode(kbReview)
                let json = try! JSONSerialization.jsonObject(with: kbReviewData, options: []) as? [String : Any]
                AF.request(EndPoints.KB_SAVE_REVIEW, method: .post, parameters: json,encoding: JSONEncoding.default, headers: headers)
                    .responseData { (response) in
                        let jsonDecoder = JSONDecoder()
                        do{
                            if(response.data != nil){
                                let parsedData =  try jsonDecoder.decode(KBResponse.self, from: response.data!)
                                completionHandler(parsedData)
                            }else{
                                let parsedData = KBResponse()
                                parsedData.error = "Unknown error has occurred"
                                parsedData.success = false;
                            }
                        }catch{
                            let parsedData = KBResponse()
                            parsedData.error = "Unknown error has occurred"
                            parsedData.success = false;
                            completionHandler(parsedData)
                        }
                }
            }
        }
    }
    
}
