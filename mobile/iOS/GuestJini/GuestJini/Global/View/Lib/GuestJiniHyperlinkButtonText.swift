//
//  GuestJiniHyperlinkButtonText.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 28/12/19.
//  Copyright © 2019 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct GuestJiniHyperlinkButtonText: View {
    var buttonText:String
    var body: some View {
         Text(buttonText)
                       .font(Fonts.RobotButtonFont)
        .underline()
                       .padding(.top, 10)
                       .padding(.bottom, 10)
                       .padding(.leading, 20)
                       .padding(.trailing, 20) .foregroundColor(Color("aquaMarine"))
    }
}

struct GuestJiniHyperlinkButtonText_Previews: PreviewProvider {
    static var previews: some View {
        GuestJiniHyperlinkButtonText(buttonText: "Forgot Password?")
    }
}
