import {Component, OnInit} from "@angular/core";

@Component({
    selector: "Home",
    moduleId: module.id,
    templateUrl: "./home.component.html"
})
export class HomeComponent implements OnInit {

    constructor() {
        // Use the component constructor to inject providers.
    }

    ngOnInit(): void {
        // Init your component properties here.
    }

    onButtonTap(): void {
        console.log("Button was pressed");
    }

    progressValue: number = 80;
    currentDay: number = new Date().getDate();
}
