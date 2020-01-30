//
//  GuestJiniRoundButtonText.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 26/12/19.
//  Copyright © 2019 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct GuestJiniRoundButtonText: View {
    var imageName: String
    var body: some View {
        Group {
        Image(imageName)
            .resizable()
            .padding(.all, 1)
        }.frame(width: 60, height: 60, alignment: .center)
    }
}

struct GuestJiniRoundButton_Previews: PreviewProvider {
    static var previews: some View {
        GuestJiniRoundButtonText(imageName: "round-done")
    }
}
