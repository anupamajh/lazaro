//
//  AppAccessRequestResponse.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 15/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation
class AppAccessRequestResponse: Decodable {
    public var success:Bool = false
    public var error:String = ""
}
