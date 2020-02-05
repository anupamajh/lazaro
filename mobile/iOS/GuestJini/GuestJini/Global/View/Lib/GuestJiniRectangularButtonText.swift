//
//  GuestJiniRectangularButtonText.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 04/02/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct GuestJiniRectangularButtonText: View {
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
            .background(Color("aquaMarine"))
            .cornerRadius(5)
        }.shadow(radius: 5)
        
        
    }
}

struct GuestJiniRectangularButtonText_Previews: PreviewProvider {
    static var previews: some View {
        GuestJiniRectangularButtonText(buttonText: "RESET PASSWORD")
    }
}

