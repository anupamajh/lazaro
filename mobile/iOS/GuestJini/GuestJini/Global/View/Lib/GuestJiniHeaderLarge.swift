//
//  GuestJiniHeaderLarge.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 28/12/19.
//  Copyright © 2019 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct GuestJiniHeaderLarge: View {
    var body: some View {
        GeometryReader { geometry in
            VStack{
                VStack{
                GuestJiniLogoImage()
                    .padding(.vertical,25)
                GuestJiniDescriptionText(description: "Experience like-minded co-living while you also perceive your passion and interests.")
                    .padding(.horizontal,30)
                    .padding(.vertical)
                }
            }   .background(Color("veryLightPink"))
            
        }
    }
}

struct GuestJiniHeaderLarge_Previews: PreviewProvider {
    static var previews: some View {
        GuestJiniHeaderLarge()
    }
}
