//
//  KB.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 21/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation
class KB: Codable, Identifiable, ObservableObject {
    public var id:String? = nil
    public var clientId:String?  = nil
    public var orgId:String? = nil
    public var topicTitle:String? = nil
    public var topicNarration:String? = nil
    public var creationTime:String? = ""
}
