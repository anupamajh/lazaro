//
//  GenericResponse.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 31/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation
class GenericResponse: Decodable {
    public var success:Bool = false
    public var error:String = ""
}
