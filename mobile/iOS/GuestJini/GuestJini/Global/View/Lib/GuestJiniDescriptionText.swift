//
//  GuestJiniDescriptionText.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 26/12/19.
//  Copyright © 2019 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct GuestJiniDescriptionText: View {
    var description:String
    var body: some View {
        VStack{
            Text(description)
                .font(Fonts.RobotRegular)
                .multilineTextAlignment(.center)
                .lineSpacing(10)
                   .lineLimit(5)
            
        }.padding()
        
    }
}

struct GuestJiniDescriptionText_Previews: PreviewProvider {
    static var previews: some View {
        GuestJiniDescriptionText(description: "The link to reset your password will be sent to your registered email address and mobile number.")
    }
}