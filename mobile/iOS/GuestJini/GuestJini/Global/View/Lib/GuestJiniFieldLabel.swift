//
//  GuestJiniFieldLabel.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 28/12/19.
//  Copyright © 2019 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct GuestJiniFieldLabel: View {
    var labelText:String
    var body: some View {
         Text(labelText)
                       .font(Fonts.RobotButtonFont)
                       .padding(.top, 10)
                       .padding(.bottom, 10)
                       .padding(.leading, 20)
                       .padding(.trailing, 20) .foregroundColor(Color("aquaMarine"))
    }
}

struct GuestJiniFieldLabel_Previews: PreviewProvider {
    static var previews: some View {
        GuestJiniFieldLabel(labelText: "REGISTERED EMAIL")
    }
}
