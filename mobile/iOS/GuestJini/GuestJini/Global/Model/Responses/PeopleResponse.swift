//
//  PeopleResponse.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 29/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation
class PeopleResponse: Decodable {
    public var myUserInfo:UserInfo? = UserInfo()
    public var otherUserInfo:UserInfo? = UserInfo()
    public var myAddressBook:AddressBook? = AddressBook()
    public var othersAddressBook:AddressBook? = AddressBook()
    public var peopleList:[AddressBook]?  = []
    public var myInterests:[UserInterests]?  = []
    public var otherInterests:[UserInterests]?  = []
    
    public var otherInterestMap:[InterestMap]?  = []
    public var myInterestMap:[InterestMap]?  = []
    public var commonInterest:[InterestMap]?  = []
    public var unCommonInterest:[InterestMap]?  = []
    
    
    public var personList:[Person]?  = []
    public var isFavourite:Int? = 0
    public var totalInterestCount:Int? = 0
    public var totalPages:Int? = 0
    public var totalRecords:Int? = 0
    public var currentRecords:Int? = 0
    public var success:Bool? = false
    public var error:String? = ""
}

