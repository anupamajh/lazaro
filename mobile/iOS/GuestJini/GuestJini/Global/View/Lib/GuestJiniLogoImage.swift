//
//  GuestJiniLogoImage.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 26/12/19.
//  Copyright © 2019 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct GuestJiniLogoImage: View {
    var body: some View {
        Group{
        Image("guesture-logo")
            .resizable()
        }.frame(width: 140, height: 50, alignment: .center)
    }
}

struct GuestJiniLogoImage_Previews: PreviewProvider {
    static var previews: some View {
        GuestJiniLogoImage()
    }
}
