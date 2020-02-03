//
//  GuestJiniRedButtonText.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 03/02/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct GuestJiniRedButtonText: View {
    var buttonText: String
    var body: some View {
        VStack{
            Text(buttonText)
                .font(Fonts.RobotButtonFont)
                .padding(.top, 10)
                .padding(.bottom, 10)
                .padding(.leading, 20)
                .padding(.trailing, 20)
                .foregroundColor(Color.white)
            .background(Color("pastelRed"))
            .cornerRadius(18)
        }.shadow(radius: 5)
        
        
    }
}

struct GuestJiniRedButtonText_Previews: PreviewProvider {
    static var previews: some View {
        GuestJiniRedButtonText(buttonText: "RESET PASSWORD")
    }
}

