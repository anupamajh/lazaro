//
//  InterestMap.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 29/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation
class InterestMap: Codable, Identifiable {
    public var interestCategory:InterestCategory? = InterestCategory()
    public var interestList:[UserInterests]?  = []
}
