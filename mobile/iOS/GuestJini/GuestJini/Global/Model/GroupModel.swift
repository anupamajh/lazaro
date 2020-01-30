//
//  Group.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 30/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation
class GroupModel: Codable, Identifiable, ObservableObject {
    public var id:String? = ""
    public var clientId:String?  = ""
    public var orgId:String? = ""
    public var interestId:String? = ""
    public var interestCategoryId:String? = ""
    public var interestCategoryName:String? = ""
    public var groupOwnerId:String? = ""
    public var groupType:Int? = 0
    public var name:String? = ""
    public var description:String? = ""
    public var creationTime:String? = ""
    public var subscribedDate:String? = ""
    public var isSubscribed:Int? = 0
    public var isInvited:Int? = 0
    public var isMatchingInterest:Int? = 0
    public var cardType:Int? = 0

}
