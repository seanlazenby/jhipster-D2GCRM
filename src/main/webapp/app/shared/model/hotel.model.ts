export interface IHotel {
  id?: string;
  hotelName?: string;
}

export class Hotel implements IHotel {
  constructor(public id?: string, public hotelName?: string) {}
}
