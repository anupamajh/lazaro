//
//  UserInterests.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 23/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation
class UserInterests: Codable, Identifiable, ObservableObject {
    public var id:String? = ""
    public var clientId:String?  = ""
    public var orgId:String? = ""
    public var userId:String? = ""
    public var interestId:String? = ""
    public var interestName:String? = ""
    public var interestCategoryName:String? = ""
    public var interestCategoryId:String? = ""
    public var isInterested:Int? = 0
    public var interestCategory:InterestCategory? = InterestCategory()
    public var creationTime:String? = ""
}
