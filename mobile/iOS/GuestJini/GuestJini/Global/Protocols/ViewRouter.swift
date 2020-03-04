//
//  ViewRouter.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 28/12/19.
//  Copyright © 2019 Prasanna Kumar Pete. All rights reserved.
//

import Foundation
import Combine
import SwiftUI

class ViewRouter: ObservableObject {
    
    let objectWillChange = PassthroughSubject<ViewRouter,Never>()
    
    init() {
        
        var isLoggedIn = false
        if(UserDefaults.standard.object(forKey: "isLoggedIn") != nil){
             isLoggedIn = UserDefaults.standard.bool(forKey: "isLoggedIn")
        }
        if( isLoggedIn == false){
            self.currentPage = ViewRoutes.LANDING_PAGE
        }else{
            self.currentPage = ViewRoutes.HOME_PAGE
        }
    }
    
    var currentPage: String = ViewRoutes.HOME_PAGE{
        didSet {
            objectWillChange.send(self)
        }
    }
    var primaryKey:String = ""
    var transactionAMount:Double = 0.0
    var peopleLogoPath:String = ""
    var searchText:String = ""
    var taskAttachment: [TaskAttachment]? = nil
    var returnPage:String = ""
    var groupType:Int = 0
    var selectedBottomBarItem = BottomBarButtonIdentifier.BOTTOM_BAR_BUTTON_SUPPORT
}
