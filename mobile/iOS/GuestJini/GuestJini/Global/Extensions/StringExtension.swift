//
//  StringExtension.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 12/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation

extension String{
    
    func convetToDateFromMySQLUTC()->String{
        let input:String = self
        if(self.trimmingCharacters(in: .whitespacesAndNewlines) == ""){
             return ""
         }
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSZZZ"
        dateFormatter.timeZone = TimeZone(abbreviation: "UTC")
        let localDate = dateFormatter.date(from: input)
        dateFormatter.timeZone = TimeZone.current
        dateFormatter.dateFormat = "dd MMM yyyy, hh:mm:ss a"
        return dateFormatter.string(from:localDate!)
    }
    
    func convetToDateFromMySQL()->String{
        let input:String = self
        if(self.trimmingCharacters(in: .whitespacesAndNewlines) == ""){
            return ""
        }
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSZZZ"
        dateFormatter.timeZone = TimeZone(abbreviation: "UTC")
        let localDate = dateFormatter.date(from: input)
        dateFormatter.dateFormat = "dd MMM yyyy, hh:mm:ss a"
        return dateFormatter.string(from:localDate!)
    }
    
    func convetToAgeFromMySQLDate()->(year :Int, month : Int, day : Int){
        let input:String = self
        if(self.trimmingCharacters(in: .whitespacesAndNewlines) == ""){
             return (0,0,0)
         }
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSZZZ"
        dateFormatter.timeZone = TimeZone(abbreviation: "UTC")
        let localDate = dateFormatter.date(from: input)
        var years = 0
        var months = 0
        var days = 0
        
        let cal = Calendar.current
        years = cal.component(.year, from: Date()) -  cal.component(.year, from: localDate!)
        let currMonth = cal.component(.month, from: Date())
        let birthMonth = cal.component(.month, from: localDate!)
        
        //get difference between current month and birthMonth
        months = currMonth - birthMonth
        //if month difference is in negative then reduce years by one and calculate the number of months.
        if months < 0
        {
            years = years - 1
            months = 12 - birthMonth + currMonth
            if cal.component(.day, from: Date()) < cal.component(.day, from: localDate!){
                months = months - 1
            }
        } else if months == 0 && cal.component(.day, from: Date()) < cal.component(.day, from: localDate!)
        {
            years = years - 1
            months = 11
        }
        
        //Calculate the days
        if cal.component(.day, from: Date()) > cal.component(.day, from: localDate!){
            days = cal.component(.day, from: Date()) - cal.component(.day, from: localDate!)
        }
        else if cal.component(.day, from: Date()) < cal.component(.day, from: localDate!)
        {
            let today = cal.component(.day, from: Date())
            let date = cal.date(byAdding: .month, value: -1, to: Date())
            
            days = date!.daysInMonth - cal.component(.day, from: localDate!) + today
        } else
        {
            days = 0
            if months == 12
            {
                years = years + 1
                months = 0
            }
        }
        return (years, months, days)
    }
}
