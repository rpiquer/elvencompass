import { Location } from "./location";

export class Marker {
    id!: number;
    latitude!: number;
    longitude!: number;
    referencedLocation!: Location;
    containerLocation!: Location;
    userId!: number;

    constructor(latitude: number, longitude:number, referencedLocation: Location, containerLocation:Location,){
        this.latitude = latitude;
        this.longitude = longitude;
        this.referencedLocation = referencedLocation;
        this.containerLocation = containerLocation;
        this.userId = 1;
    }
}
