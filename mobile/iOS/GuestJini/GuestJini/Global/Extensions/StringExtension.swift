//
//  StringExtension.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 12/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation

extension String{
    
    func convetToDate()->String{
        let input:String = self
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSZZZ"
        dateFormatter.timeZone = TimeZone(abbreviation: "UTC")
        let localDate = dateFormatter.date(from: input)
        dateFormatter.timeZone = TimeZone.current
        dateFormatter.dateFormat = "dd MMM yyyy, hh:mm:ss a"
       return dateFormatter.string(from:localDate!)
    }
}
