//
//  GuestJiniFieldError.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 30/12/19.
//  Copyright © 2019 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct GuestJiniFieldError: View {
    
    var body: some View {
        VStack{
            HStack{
            Text("This field is required")
                .font(Fonts.RobotRegular)
            .foregroundColor(Color("terracotta"))
                Spacer()
            }
        }
    }
}

struct GuestJiniFieldError_Previews: PreviewProvider {
    static var previews: some View {
        GuestJiniFieldError()
    }
}
