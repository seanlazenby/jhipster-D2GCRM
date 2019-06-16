export interface ILead {
  id?: string;
  leadName?: string;
  leadPhone?: string;
  leadid?: string;
  title?: string;
  status?: string;
  active?: string;
  hotel?: string;
  rooms?: string;
  contact?: string;
  officetel?: string;
  cell?: string;
  street1?: string;
  street2?: string;
  city?: string;
  state?: string;
  postalcode?: string;
  country?: string;
  website?: string;
  email?: string;
  followup?: string;
  comment?: string;
}

export class Lead implements ILead {
  constructor(
    public id?: string,
    public leadName?: string,
    public leadPhone?: string,
    public leadid?: string,
    public title?: string,
    public status?: string,
    public active?: string,
    public hotel?: string,
    public rooms?: string,
    public contact?: string,
    public officetel?: string,
    public cell?: string,
    public street1?: string,
    public street2?: string,
    public city?: string,
    public state?: string,
    public postalcode?: string,
    public country?: string,
    public website?: string,
    public email?: string,
    public followup?: string,
    public comment?: string
  ) {}
}
