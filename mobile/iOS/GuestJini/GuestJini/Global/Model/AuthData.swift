//
//  AuthData.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 30/12/19.
//  Copyright © 2019 Prasanna Kumar Pete. All rights reserved.
//

import Foundation
class AuthData : Decodable{
    public var access_token:String = ""
    public var token_type:String = ""
    public var refresh_token:String = ""
    public var expires_in:Int = 0
    
}
