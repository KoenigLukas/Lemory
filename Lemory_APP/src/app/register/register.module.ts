import { NgModule, NO_ERRORS_SCHEMA } from "@angular/core";
import { NativeScriptCommonModule } from "nativescript-angular/common";

import { RegisterRoutingModule } from "./register-routing.module";
import { RegisterComponent } from "./register.component";

@NgModule({
    imports: [
        NativeScriptCommonModule,
        RegisterRoutingModule
    ],
    declarations: [
        RegisterComponent
    ],
    schemas: [
        NO_ERRORS_SCHEMA
    ]
})
export class HomeModule { }
