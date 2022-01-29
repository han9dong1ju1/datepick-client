//
//  UIDevice+Bool.swift
//  iosApp
//
//  Created by Harry on 2022/01/25.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI

extension UIDevice {
    static var isIPad: Bool {
        UIDevice.current.userInterfaceIdiom == .pad
    }
    
    static var isIPhone: Bool {
        UIDevice.current.userInterfaceIdiom == .phone
    }
}
