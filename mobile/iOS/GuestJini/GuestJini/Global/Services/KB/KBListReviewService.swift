//
//  KBListReviewService.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 22/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation
import Alamofire
import SwiftUI

class KBListReviewService:ObservableObject{
    @Published var kbReviewResponse = KBReviewResponse()
    @ObservedObject var viewRouter: ViewRouter
    @Published var kbReviewList:[KBReview] = []
    @Published var fetchComplete:Bool = false
    
    var checkTokenService:CheckTokenService
    
    init(viewRouter: ViewRouter, kbId:String) {
        self.viewRouter = viewRouter;
        self.checkTokenService = CheckTokenService(viewRouter: viewRouter)
        self.getKBList(kbId: kbId) { (response) in
            self.kbReviewResponse = response
            self.kbReviewList = response.kbReviewList!;
            self.fetchComplete = true
        }
    }
    
    func getKBList(kbId:String, completionHandler: @escaping(KBReviewResponse)->Void) -> Void {
        checkTokenService.CheckToken { (checkStatus) in
            if(checkStatus){
                let headers: HTTPHeaders = [
                    "Authorization": "Bearer \(UserDefaults.standard.string(forKey: "access_token")!)",
                    "Accept": "application/json"
                ]
                
                let parameters = ["kbId" : kbId]
                AF.request(EndPoints.KB_GET_ALL_REVIEWS, method: .post, parameters: parameters,encoding: JSONEncoding.default, headers: headers)
                    .responseData { (response) in
                        let jsonDecoder = JSONDecoder()
                        do{
                            let parsedData = try jsonDecoder.decode(KBReviewResponse.self, from: response.data!)
                            completionHandler(parsedData)
                        }catch{
                            let parsedData = KBReviewResponse()
                            completionHandler(parsedData)
                        }
                        
                }
            }
            
        }
    }
}
