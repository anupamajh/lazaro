//
//  Person.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 29/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation
class Person: Codable, Identifiable {
    public var addressBook:AddressBook? = AddressBook()
    public var userInterestsList:[UserInterests]?  = []
    public var isFavourite:Int? = 0
    
}
