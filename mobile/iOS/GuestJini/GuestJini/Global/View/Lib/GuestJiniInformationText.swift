//
//  GuestJiniInformationText.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 28/12/19.
//  Copyright © 2019 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct GuestJiniInformationText: View {
   var information:String
    var body: some View {
        VStack{
            Text(information)
                .font(Fonts.RobotRegular) .multilineTextAlignment(.leading)
                   .lineLimit(200)
        }
        
    }
}

struct GuestJiniInformationText_Previews: PreviewProvider {
    static var previews: some View {
        GuestJiniInformationText(information: "Lorem")
    }
}
