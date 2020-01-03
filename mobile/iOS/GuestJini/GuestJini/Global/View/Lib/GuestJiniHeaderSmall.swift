//
//  GuestJiniHeaderSmall.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 28/12/19.
//  Copyright © 2019 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct GuestJiniHeaderSmall: View {
    var body: some View {
        GeometryReader { geometry in
            VStack{
                HStack{
                    Spacer()
               
                }
            }   .background(Color("veryLightPink"))
            
        }
    }
}

struct GuestJiniHeaderSmall_Previews: PreviewProvider {
    static var previews: some View {
        GuestJiniHeaderSmall()
    }
}
